package cn.com.mfish.oauth.core.validator;

import org.springframework.stereotype.Component;

/**
 * @author qiufeng
 * @date 2020/2/16 19:10
 */
@Component
public class GetCodeValidator extends MultipleValidator {
    public GetCodeValidator(){
        this.validateClientList.add(ClientIdExistValidator.class);
        this.validateClientList.add(AllowCodeValidator.class);
        this.validateClientList.add(RedirectUriExistValidator.class);
    }
}
