package cn.zry;

import cn.zry.modules.security.config.Constant;
import cn.zry.modules.security.rest.RestAuthUtils;
import cn.zry.modules.security.utils.JwtService;
import cn.zry.modules.web.BaseController;
import cn.zry.modules.web.common.ApiResponse;
import cn.zry.modules.web.common.SimpleApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = {"/", "index"})
    @ResponseBody
    public String index() {
        return "apis 1.0";
    }

    /**
     * 登陆接口
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SimpleApiResponse login() {
        String token = null;
        try {
            String subject = jwtService.generalSubject(RestAuthUtils.currentSid());
            token = jwtService.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "success", token);
    }

}
