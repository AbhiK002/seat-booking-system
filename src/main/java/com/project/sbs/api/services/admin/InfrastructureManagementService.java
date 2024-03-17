package com.project.sbs.api.services.admin;

import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;

public interface InfrastructureManagementService {
    Office createOffice(String OfficeName);

    Floor createFloor(Integer floorId, Office officeId);
}
