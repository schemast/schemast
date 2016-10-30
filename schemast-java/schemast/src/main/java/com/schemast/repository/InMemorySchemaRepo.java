package com.schemast.repository;

import com.schemast.schemas.Schema;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemorySchemaRepo implements SchemaRepo {
    private Map<String, Map<Integer, Schema>> nMap = new HashMap<>();

    @Override
    public void put(Schema schema) {
        if (schema == null) throw new NullPointerException("Schema cannot be null");

        Map<Integer, Schema> vMap = nMap.getOrDefault(schema.getName(), new HashMap<>());
        vMap.put(schema.getVersion(), schema);
        nMap.put(schema.getName(), vMap);
    }

    @Override
    public Schema get(String schemaName) {
        Map<Integer, Schema> vMap = nMap.get(schemaName);
        if (vMap == null || vMap.isEmpty()) return null;

        Integer highestVersion = Collections.max(vMap.keySet());
        return vMap.get(highestVersion);
    }

    @Override
    public Schema get(String schemaName, int version) {
        Map<Integer, Schema> vMap = nMap.get(schemaName);
        if (vMap == null || vMap.isEmpty()) return null;

        return vMap.get(version);
    }

    @Override
    public boolean delete(String schemaName) {
        Map<Integer, Schema> vMap = nMap.remove(schemaName);
        return vMap != null && !vMap.isEmpty();
    }

    @Override
    public boolean delete(String schemaName, int version) {
        Map<Integer, Schema> vMap = nMap.get(schemaName);
        if (vMap == null || vMap.isEmpty()) return false;

        return vMap.remove(version) != null;
    }

}
