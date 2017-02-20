package works.softwarethat.lottery;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import works.softwarethat.lottery.model.TicketTemplate;
import works.softwarethat.lottery.services.CorporationsService;
import works.softwarethat.lottery.services.PersonsService;
import works.softwarethat.lottery.services.SellersService;
import works.softwarethat.lottery.services.TicketTemplateService;
import works.softwarethat.lottery.services.UsersService;

/**
 * Class handles singleton instantiation.
 *
 * @author mortena@gmail.com
 */

public interface Services {

    UsersService getUsersService();
    PersonsService getPersonsService();
    CorporationsService getCorporationService();
    TicketTemplateService getTicketTemplateService();
    SellersService getSellersService();

    static Services getServices() {
        return (Services) Proxy.newProxyInstance(Services.class.getClassLoader(), new Class[]  {Services.class}, new ServicesProxy());
    }


    static class ServicesProxy implements InvocationHandler {
        private final Map<Class, Object> instances = new HashMap();

        public Object get(Class clazz) {
            if (instances.containsKey(clazz)) {
                return instances.get(clazz);
            }
            try {
                Constructor constructor = clazz.getConstructor();
                Object instance = constructor.newInstance();
                instances.put(clazz, instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException("Expected default constructor");
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class returnType = method.getReturnType();
            if (instances.containsKey(returnType)) {
                return instances.get(returnType);
            }
            Constructor constructor = returnType.getConstructor();
            Object instance = constructor.newInstance();
            instances.put(returnType, instance);
            return instance;
        }
    }
}
