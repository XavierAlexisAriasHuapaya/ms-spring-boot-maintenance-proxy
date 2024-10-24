package dev.arias.huapaya.ms_maintenance_proxy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasterDetail {

    private Long id;

    private String description;

    private String value;

    private Boolean status;

}
