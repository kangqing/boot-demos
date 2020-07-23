package realm;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import jdbc.JdbcConnection;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.util.Set;

/**
 * @Description  Realm 在 Shiro里到底扮演什么角色呢？
 * 当应用程序向 Shiro 提供了 账号和密码之后， Shiro 就会问 Realm 这个账号密码是否对， 如果对的话，其所对应的用户拥有哪些角色，哪些权限。
 * 所以Realm 是什么？ 其实就是个中介。 Realm 得到了 Shiro 给的用户和密码后，有可能去找 ini 文件，也可以去找数据库。
 * Realm 就是干这个用的，它才是真正进行用户认证和授权的关键地方。
 * @Author yx
 * @Data 2020/7/22 17:29
 */
public class DatabaseRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //能进入到这里，表示账号已经通过验证了,开始授权
        String username = (String) principalCollection.getPrimaryPrincipal();
        //获取角色和权限
        Set<String> roleList = new JdbcConnection().getRoleList(username);
        Set<String> permitList = new JdbcConnection().getPermitList(username);
        //要授权的对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roleList);
        simpleAuthorizationInfo.setStringPermissions(permitList);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从之前封装的UsernamePasswordToken中获取用户填写的用户名、密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getPrincipal().toString();
        String password = String.valueOf(token.getPassword());
        String salt = "csau8za98xa@^*&7==-??.>smjhd";
        String encodePass = SecureUtil.sha256(password + salt);
        //从数据库中获取用户名的密码
        String passwordInDB = new JdbcConnection().getPasswordByUsername(username);
        if (!encodePass.equals(passwordInDB) || StrUtil.isBlank(passwordInDB)) {
            throw new AuthenticationException();
        }
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
