package hainguyen.impala.injection.component;

import dagger.Subcomponent;
import hainguyen.impala.injection.module.UserModule;
import hainguyen.impala.injection.scope.UserScope;

@UserScope
@Subcomponent(
        modules = {
                UserModule.class})
public interface UserComponent {

}
