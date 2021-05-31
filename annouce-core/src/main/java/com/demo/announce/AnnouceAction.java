package com.demo.announce;

public @interface AnnouceAction {
    String dest() default "default";
}
