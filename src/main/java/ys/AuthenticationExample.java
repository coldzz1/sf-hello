/*
package ys;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationExample {
    private static AuthenticationManager am=new SimpleAuthenticationManager();

    public static void main(String[] args) {
        String name ="";
        String password="";
        try {
                         // request就是第一步，使用name和password封装成为的token
                        Authentication request = new UsernamePasswordAuthenticationToken(name, password);
                       // 将token传递给Authentication进行验证
                         Authentication result = am.authenticate(request);
                         SecurityContextHolder.getContext().setAuthentication(result);

                     } catch (AuthenticationException e) {
                         System.out.println("认证失败：" + e.getMessage());
                     }
                 System.out.println("认证成功，Security context 包含：" + SecurityContextHolder.getContext().getAuthentication());
             }
}


//为认证管理类实现一个子类 自定义校验方式
class SimpleAuthenticationManager implements AuthenticationManager{


     static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    // 利用静态代码块构建一个角色列表
     static {
                 AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
                 AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER2"));
             }

     //验证方法  通过传入Authentication
    public Authentication authenticate(Authentication  auth)throws AuthenticationException{
         //这里我们自定义了验证通过条件,username与password相同就可以通过验证
         if(auth.getName().equals(auth.getCredentials())){
             return new UsernamePasswordAuthenticationToken(auth.getName(),auth.getCredentials(),AUTHORITIES);
         }
         //没有认证通过则抛出密码错误异常
         throw new BadCredentialsException("Bad Credentials");
    }

}
*/
