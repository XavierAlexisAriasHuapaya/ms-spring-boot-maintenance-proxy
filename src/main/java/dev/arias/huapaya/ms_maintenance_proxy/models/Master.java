package dev.arias.huapaya.ms_maintenance_proxy.models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Master {
    
    private Long id;

    private String description;

    private List<MasterDetail> master_details;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Boolean status;

}
