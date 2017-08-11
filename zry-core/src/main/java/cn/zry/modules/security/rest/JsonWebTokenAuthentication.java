package cn.zry.modules.security.rest;

import cn.zry.modules.http.HttpStatus;
import cn.zry.modules.mapper.JsonMapper;
import cn.zry.modules.security.common.LoginPara;
import cn.zry.modules.security.utils.JwtService;
import cn.zry.modules.web.common.ApiResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现json web token认证的Spring MVC拦截器
 * 认证信息暂时只使用Redis作为认证信息的保存容器。
 *
 * @author qianlu
 */
public class JsonWebTokenAuthentication implements HandlerInterceptor {

    private IAuthenticationManager authenticationManager;

    private ISessionManager sessionManager;

    private JwtService jwtService;

    private JsonMapper jsonMapper = new JsonMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers,Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Authorization,Origin,X-Requested-With,Content-Type,Accept");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.SC_OK);
            return false;
        }

        try {
            //支持每次认证json web token
            String jwt = RestAuthUtils.getToken(request);
            Claims claims = jwtService.parseJWT(jwt);
            LoginPara loginPara = jwtService.reverseSubject(claims.getSubject(), LoginPara.class);

            Session session = retriveSession(loginPara.getSid());
            if (session != null) {
                bind2Thread(session);
                return true;
            }
        } catch (RestAuthticationException e) {
            // Run the return false branch
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        } catch (ExpiredJwtException e) {
            response.getWriter().append(jsonMapper.toJson(new ApiResponse(ApiResponse.CODE_TOKEN_DISABLED, "token expired")));
            return false;
        }
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        return false;
    }

    private Session retriveSession(String sid) {
        return sid == null ? null : sessionManager.loadSession(sid);
    }

    private void bind2Thread(Session s) {
        RestAuthUtils.setSession(s);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        RestAuthUtils.cleanThreadLocalSession();
    }


    /**
     * ↓ 依赖注入
     */

    public void setAuthenticationManager(IAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setSessionManager(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }
}