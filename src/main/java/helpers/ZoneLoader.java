package helpers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Alexander Nyström(dv15anm) on 12/12/2016.
 */
public class ZoneLoader {

    private Class<?> zoneClass;
    private Object zone;
    private Method landOn;
    private ErrorMessages errorMessages;
    private boolean error;

    public ZoneLoader(ErrorMessages messages) {
        errorMessages = messages;
        error = false;
    }

    /**
     * Loads the given zone class with reflection
     * @param className name of the class that will be loaded
     * @return true if it was successfully loaded else false
     */
    public boolean loadZone(String className) {
        if(isValidClass(className) && implementsZone(zoneClass)) {
            try {
                zone = zoneClass.newInstance();
                Method[] methods = zoneClass.getMethods();
                for(Method method: methods) {
                    if(method.getName().compareTo("landOn") == 0) {
                        landOn = method;
                        return true;
                    }
                }
            } catch (InstantiationException e) {
                error = true;
               errorMessages.setInstantiationException("The class could not" +
                       " be initialized. This could be caused " +
                       "by the class being abstract" +
                       " or an array class or a primitive type.");
            } catch (IllegalAccessException e) {
                error = true;
                errorMessages.setIllegalAccessException("Could not access " +
                        "the class or it's constructor.");
            }
        }
        return false;
    }

    /**
     * Checks that the given class is valid by making sure it's not an interface
     * and has an constructor that takes 0 parameters.
     * @param className name of the class to be checked
     * @return true if it is valid else false
     */
    private boolean isValidClass(String className) {
        try {
            boolean valid = false;
            String path = ZoneLoader.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI().getPath();
            File f = new File(path);
            URL[] urls = {f.getParentFile().toURI().toURL()};
            if (urls[0] == null) {
                error = true;
                errorMessages.setFileError("Could not find class file: "+
                                            className);
            } else {
                zoneClass = Class.forName(className,true,new URLClassLoader(urls));
                if (zoneClass.isInterface()) {
                    error = true;
                    errorMessages.setInterFaceError("Class is Interface and" +
                            " cannot be instanced");
                    return false;
                }
                Constructor<?>[] constructors = zoneClass.getConstructors();
                for (Constructor con : constructors) {
                    if (con.getParameterCount() == 0) {
                        valid = true;
                    }
                }
                if (!valid) {
                    error = true;
                    errorMessages.setConstructorError("Could not find a" +
                            " constructor that does not take any arguments");
                }

                return valid;
            }
        } catch (ClassNotFoundException e) {
            error = true;
            errorMessages.setClassNotFoundException("Could not find class: "
                                                    + className);
        } catch (NoClassDefFoundError e) {
            error = true;
            errorMessages.setNoClassDefFoundError("Could not find class: " +
                            className + " (Check spelling)");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks that the class implements the interface Zone
     * @param cls - instance of the class
     * @return true if it implements the interface else false.
     */
    private boolean implementsZone(Class<?> cls) {
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (anInterface.getName().compareTo("tile.Zone") == 0) {
                return true;
            }
        }
        error = true;
        errorMessages.setImplementationError("Class does not implement the " +
                                                "interface Zone.");
        return false;
    }

    /**
     *
     * @return true if an error was encountered else false
     */
    public boolean isError() {
        return error;
    }

    /**
     * Return the zone class as an object
     * @return The zone class as an object
     */
    public Object getZone() {
        return zone;
    }

    /**
     * Returns the landOn method from the zone class
     * @return The landOn method.
     */
    public Method getLandOn() {
        return landOn;
    }
}
