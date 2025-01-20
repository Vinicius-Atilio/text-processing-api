package vsi.com.br.textprocessing.shared.exceptions.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import vsi.com.br.textprocessing.shared.contracts.VSILoggerService;
import vsi.com.br.textprocessing.shared.exceptions.VSIException;

@Component
public class VSILoggerAdapter implements VSILoggerService {
    private String contextName;
    private String environment;
    private Logger logger;

    public VSILoggerAdapter(Environment environment, @Value("${spring.application.name}") String contextName) {
        this.contextName = contextName;
        this.environment = String.join(",", environment.getActiveProfiles());
        this.logger = LoggerFactory.getLogger(contextName);
    }

    @Override
    public void logException(VSIException ex) {
        this.logger.error(ex.getMessage(), ex);
    }
}
