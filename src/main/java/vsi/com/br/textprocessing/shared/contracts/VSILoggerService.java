package vsi.com.br.textprocessing.shared.contracts;

import vsi.com.br.textprocessing.shared.exceptions.VSIException;

public interface VSILoggerService {
    void logException(VSIException ex);
}
