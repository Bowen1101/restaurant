package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Role;

public interface RoleDao {
    Role getRoleByName(String name);
}
