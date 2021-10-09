package com.glicemap.builder;

import com.glicemap.dto.MedicDTO;
import com.glicemap.dto.UserDTO;
import com.glicemap.dto.UserMedicInfoDTO;
import org.springframework.stereotype.Component;

@Component
public final class UserMedicInfoBuilder {
    private UserDTO user;
    private MedicDTO medic;

    public UserMedicInfoBuilder setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public UserMedicInfoBuilder setMedic(MedicDTO medic) {
        this.medic = medic;
        return this;
    }

    public UserMedicInfoDTO build() {
        UserMedicInfoDTO userMedicInfoDTO = new UserMedicInfoDTO();
        userMedicInfoDTO.setUser(user);
        userMedicInfoDTO.setMedic(medic);
        return userMedicInfoDTO;
    }
}
