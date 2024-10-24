package dev.arias.huapaya.ms_maintenance_proxy.service.interfaces;

import java.util.Map;

import dev.arias.huapaya.ms_maintenance_proxy.models.Master;

public interface MasterService {

    public Map<String, Object> findAll();

    public Map<String, Object> save(Master master);

}
