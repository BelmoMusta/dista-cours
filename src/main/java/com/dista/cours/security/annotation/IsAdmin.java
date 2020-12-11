package com.dista.cours.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('admin')")
public @interface IsAdmin {
}