package cn.zry.file.controller;


import cn.zry.file.dto.Chun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by lenovo on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/")
public class FileUploadController {
    private String directory = "d:/up/";

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "upFile")
    public boolean upFile(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Chun Chun, @RequestParam("file") MultipartFile multipartFile) {
        try {
            String upFilename = multipartFile.getOriginalFilename();
            String name = upFilename.substring(0, upFilename.lastIndexOf("."));
            String filePath = directory + name + "-" + Chun.getResumableChunkNumber();
            File f = new File(filePath);
            boolean b = false;
            b = new File(directory + upFilename).exists();
            if (b)
                return true;
            b = sizeCompare(f, Chun.getResumableCurrentChunkSize());
            if (b)
                return true;
            multipartFile.transferTo(f);
            return true;
        } catch (IOException e) {
//            e.printStackTrace();
            return false;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "completeFile", method = RequestMethod.POST)
    public boolean completeFile(String fullName) {
        if (fullName == null || fullName.equals(""))
            return false;
        boolean b = mergeFile(fullName);
        return true;
    }

    /**
     * 合并文件
     *
     * @return
     */
    private boolean mergeFile(String fullName) {
        if (fullName == null || fullName.equals(""))
            return false;
        FileOutputStream out = null;
        FileChannel outf = null;
        File dFile = new File(directory + fullName);
        try {
            String preName = fullName.substring(0, fullName.lastIndexOf("."));
            File f = new File(directory);
            File[] files = f.listFiles((dir, name) -> name.startsWith(preName + "-"));
            if (files.length < 1)
                return false;
            if (dFile.exists())
                return true;
            dFile.delete();
            dFile.createNewFile();
            out = new FileOutputStream(dFile);
            outf = out.getChannel();
            FileChannel inf = null;
            ByteBuffer allocate = null;
            for (File file : files) {
                FileInputStream in = new FileInputStream(file);
                inf = in.getChannel();
                outf = out.getChannel();
                allocate = ByteBuffer.allocate(10 * 1024);
                while (inf.read(allocate) != -1) {
                    allocate.flip();
                    outf.write(allocate);
                    allocate.clear();
                }
                inf.close();
                in.close();
                file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            dFile.delete();
            return false;
        } finally {
            try {
                outf.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件大小和上传的大小是否一致
     *
     * @return
     */
    private boolean sizeCompare(File file, long upSize) {
        long length = file.length();
        if (length == upSize)
            return true;
        else
            return false;
    }
}
