package com.project.sbs.api.services;

import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;

public interface InfrastructureManagementService {
    Office createOffice(String OfficeName);

    Floor createFloor(String floorId, String officeId);
}
