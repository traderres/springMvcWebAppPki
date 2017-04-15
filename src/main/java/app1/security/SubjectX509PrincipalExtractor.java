package app1.security;

import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

import java.security.cert.X509Certificate;

/**
 * Created by adam on 1/6/2016.
 */
public class SubjectX509PrincipalExtractor implements X509PrincipalExtractor
{

    public Object extractPrincipal(X509Certificate aClientCert)
    {
        Object principal = aClientCert.getSubjectX500Principal().getName();
        return principal;
    }

}