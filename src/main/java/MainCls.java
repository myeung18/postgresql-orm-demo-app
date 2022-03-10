import io.quarkus.kubernetes.service.binding.runtime.ServiceBinding;
import org.jboss.logging.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MainCls {
    public static void main(String[] args) {

        MainCls cls = new MainCls();
//        cls.method();


        cls.readPath();
    }

    void method() {
        Optional<Map<String, String>> opt = Optional.of(new HashMap<>());
        opt = Optional.ofNullable(null);
        opt = Optional.empty();


        System.out.println(opt.isPresent());
    }

    private static final Logger log = Logger.getLogger(MainCls.class);
    List<ServiceBinding> serviceBindings;
    public void readPath() {
        String bindingRoot = "/home/myeung/gosrc/gomod/sbo-go-library/bindingcrdb";
        Path p = Paths.get(bindingRoot);
        if (!Files.exists(p)) {
            log.warn("Service Binding root '" + p.toAbsolutePath().toString() + "' does not exist");
            serviceBindings = Collections.emptyList();
            return;
        }
        if (!Files.isDirectory(p)) {
            throw new IllegalArgumentException("Service Binding root '" + p + "' is not a directory");
        }

        File[] files = p.toFile().listFiles();
        if (files == null) {
            log.warn("Service Binding root '" + p.toAbsolutePath().toString() + "' does not contain any sub-directories");
            serviceBindings = Collections.emptyList();
        } else {
            log.debug("Found " + files.length + " potential Service Binding directories");
            serviceBindings = new ArrayList<>(files.length);
            for (File f : files) {
                ServiceBinding sb = new ServiceBinding(f.toPath());
                serviceBindings.add(sb);
                if (log.isDebugEnabled()) {
                    log.debug(String.format("Directory '%s' contains %d %s and will be used as Service Binding %s",
                            f.toPath().toAbsolutePath(), sb.getProperties().size(),
                            sb.getProperties().size() == 1 ? "property" : "properties", sb.toString()));
                }
            }
            serviceBindings.sort(new Comparator<ServiceBinding>() {
                @Override
                public int compare(ServiceBinding o1, ServiceBinding o2) {
                    if (!o1.getName().equals(o2.getName())) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return o1.getProvider().compareTo(o2.getProvider());
                }
            });
        }
    }
    /*
StringBuilder sslparam = new StringBuilder();
String sslmode = binding.getProperties().getOrDefault("sslmode", "");
String sslRootCert = binding.getProperties().getOrDefault("sslrootcert", "");
if (!"".equals(sslmode)) {
    sslparam.append("sslmode=").append(sslmode);
}
if (!"".equals(sslRootCert)) {
    String and = "";
    if (!"".equals(sslmode)) and = "&";
    sslparam.append(and + "sslrootcert=")
            .append(binding.getBindingDirectory() + "/" + sslRootCert);
}

String options = binding.getProperties().getOrDefault("options", "");
try {
    options = options.length() > 0 ? "options=" + encoding(options.substring(1)).replace("+", "%20") : "";
} catch (Exception e) {
    throw new IllegalArgumentException("failed to encode options params" + options, e);
}
     */
}
