package com.sinan.javademo.apiapplication;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The entry point for all resources. It defines the parent path for all APIs inside @ApplicationPath.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@ApplicationPath("/api")
public class RestApplication extends Application {

}