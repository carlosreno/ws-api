package ws.api.wsapi.service;


import ws.api.wsapi.model.jpa.UserType;

import java.util.List;

public interface UserTypeService {
    List<UserType> findAll();
}
