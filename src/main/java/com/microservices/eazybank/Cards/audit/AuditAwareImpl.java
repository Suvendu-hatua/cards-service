package com.microservices.eazybank.Cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware {
    /**
     * @return
     */
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("Banking_MS");
    }
}
