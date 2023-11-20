package com.speerd_api

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.boot.model.naming.Identifier
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment

class CustomPhysicalNamingStrategy : PhysicalNamingStrategyStandardImpl() {
    override fun toPhysicalColumnName(name: Identifier, context: JdbcEnvironment): Identifier {
        // Return the identifier as is, without converting to snake case
        return name
    }
}
