package com.bachelor.Heartapp.model.helpertypes;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class GenericFloatArrayUserType<T extends Serializable> implements UserType {

    protected static final int[] SQL_TYPES = { Types.ARRAY };

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return this.deepCopy(cached);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Float[]) this.deepCopy(value);
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {

        if (x == null) {
            return y == null;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session,
            Object owner)
            throws HibernateException, SQLException {
        if (resultSet.wasNull()) {
            return null;
        }
        if (resultSet.getArray(names[0]) == null) {
            return new Double[0];
        }

        Array array = resultSet.getArray(names[0]);
        Float[] javaArray = (Float[]) array.getArray();
        return javaArray;
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index,
            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        Connection connection = statement.getConnection();
        if (value == null) {
            statement.setNull(index, SQL_TYPES[0]);
        } else {
            Float[] castObject = (Float[]) value;
            Array array = connection.createArrayOf("decimal", castObject);
            statement.setArray(index, array);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class<Float[]> returnedClass() {
        return Float[].class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.ARRAY };
    }

}