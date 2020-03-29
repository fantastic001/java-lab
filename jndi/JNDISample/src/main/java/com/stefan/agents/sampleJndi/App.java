package com.stefan.agents.sampleJndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.InitialContext;



/**
 * Hello world!
 *
 */
public class App {
    public static Hashtable<String, Object> setupLdap() {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDITutorial");

        // Authenticate as S. User and password "mysecret"
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin, dc=example, dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "admin");
        return env;
    }
    public static Hashtable<String, Object> setupLocal() {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.fscontext.RefFSContextFactory");
        return env;
    }
    public static void main(String[] args) {
        // Set up the environment for creating the initial context
        
        Context ctx;
        // Create the initial context
        try {
            String message = ""; 
            ctx = new InitialDirContext(setupLdap());
            ctx.bind("ou=favorite, ou=fruit", message);
            message = "hello";
            System.out.println((String) ctx.lookup("ou=msg, ou=root"));

        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
