package cn.zry.modules.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QL on 2017/1/17.
 */
public class CommandBuildUtil {

    public static List<String> exeBashWithSync(String command) throws Exception {
        Process process = null;
        List<String> commandList = new ArrayList<>();
        commandList.add("/bin/bash");
        commandList.add("-c");
        commandList.add(command);

        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        processBuilder.environment();
        processBuilder.redirectErrorStream(true);
        process = processBuilder.start();

        return readAllOutputLog(process);
    }

    public static List<String> exeShWithSync(String command) throws Exception {
        Process process = null;
        List<String> commandList = new ArrayList<>();
        commandList.add("sh");
        commandList.add("-c");
        commandList.add(command);

        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        processBuilder.environment();
        processBuilder.redirectErrorStream(true);
        process = processBuilder.start();
        return readAllOutputLog(process);
    }

    private static List<String> readAllOutputLog(Process process) throws Exception {
        List<String> processList = new ArrayList<String>();
        if (process != null) {
            //读取标准输出流
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            //读取标准错误流
            BufferedReader brError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8"));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                processList.add(line);
            }

            String errline = null;
            while ((errline = brError.readLine()) != null) {
                processList.add(errline);
            }
            //waitFor()判断Process进程是否终止，通过返回值判断是否正常终止。0代表正常终止
            int c = process.waitFor();
            if (c != 0) {
                throw new Exception("[CommandBuildUtil] 执行shell异常终止");
            }
        } else {
            throw new Exception("[CommandBuildUtil] ProcessBuilder.start()返回process进程为null");
        }
        return processList;
    }

}
