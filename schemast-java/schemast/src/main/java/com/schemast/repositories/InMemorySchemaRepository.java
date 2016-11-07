package com.schemast.repositories;

import com.schemast.schemas.Schema;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemorySchemaRepository implements SchemaRepository {

    // namespace -> ( name -> ( version -> Schema ) )
    private Map<String, Map<String, Map<Integer, Schema>>> nsMap = new HashMap<>();

    private void validateNamespace(String namespace) {
        if (namespace == null || namespace.isEmpty())
            throw new SchemaRepositoryException("Invalid namespace: " + namespace);
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty())
            throw new SchemaRepositoryException("Invalid name: " + name);
    }

    private Map<String, Map<Integer, Schema>> getNameMap(String namespace) {
        validateNamespace(namespace);
        Map<String, Map<Integer, Schema>> m = nsMap.getOrDefault(namespace, new HashMap<>());
        if (m.isEmpty()) nsMap.put(namespace, m);
        return m;
    }

    private Map<Integer, Schema> getVersionMap(String namespace, String name) {
        validateName(name);
        Map<String, Map<Integer, Schema>> nameMap = getNameMap(namespace);
        Map<Integer, Schema> vMap = nameMap.getOrDefault(name, new HashMap<>());
        if (vMap.isEmpty()) nameMap.put(name, vMap);
        return vMap;
    }

    @Override
    public void put(Schema s) {
        if (s == null) throw new SchemaRepositoryException("Schema cannot be null");

        Map<Integer, Schema> vMap = getVersionMap(s.getNamespace(), s.getName());
        vMap.put(s.getMetadata().getVersion(), s);
    }

    @Override
    public Schema get(String namespace, String name) {
        Map<Integer, Schema> vMap = getVersionMap(namespace, name);
        if (vMap.isEmpty()) {
            return null;
        } else {
            Integer highestVersion = Collections.max(vMap.keySet());
            return vMap.get(highestVersion);
        }
    }

    @Override
    public Schema get(String namespace, String name, int version) {
        Map<Integer, Schema> vMap = getVersionMap(namespace, name);
        if (vMap.isEmpty())
            return null;
        else
            return vMap.get(version);
    }

    @Override
    public boolean delete(String namespace) {
        validateNamespace(namespace);
        return nsMap.remove(namespace) != null;
    }

    @Override
    public boolean delete(String namespace, String name) {
        validateName(name);
        return getNameMap(namespace).remove(name) != null;
    }

    @Override
    public boolean delete(String namespace, String name, int version) {
        return getVersionMap(namespace, name).remove(version) != null;
    }
}
