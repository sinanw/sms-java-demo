package com.sinan.javademo.apiapplication.resource;

import com.google.gson.Gson;

/**
 * The parent class of all resources. It's used to implement common logic or to do necessary initialization.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public abstract class SMSResource {

    /**
     * An instance of Gson library to serialize/deserialize APIs input and output to/from json.
     *
     * @see <a href="https://github.com/google/gson">Google Gson</a>
     */
    protected final Gson gson = new Gson();
}
