package cn.com.mfish.oauth.core.validator;

import cn.com.mfish.oauth.core.common.CheckWithResult;
import cn.com.mfish.oauth.core.common.Utils;
import cn.com.mfish.oauth.core.model.RedisAccessToken;
import cn.com.mfish.oauth.core.service.OAuth2Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author qiufeng
 * @date 2020/2/17 19:06
 */
@Component
public class AccessTokenValidator implements IBaseValidator<RedisAccessToken> {
    @Resource
    OAuth2Service oAuth2Service;

    @Override
    public CheckWithResult<RedisAccessToken> validate(HttpServletRequest request, CheckWithResult<RedisAccessToken> result) {
        RedisAccessToken token;
        if (result == null || result.getResult() == null) {
            result = new CheckWithResult<>();
            String accessToken = Utils.getAccessToken(request);
            if (StringUtils.isEmpty(accessToken)) {
                return result.setSuccess(false).setMsg("错误:token不正确");
            }
            token = oAuth2Service.getToken(accessToken);
            if (token == null) {
                return result.setSuccess(false).setMsg("错误:token不正确");
            }
            return result.setResult(token);
        }
        return result;
    }
}
