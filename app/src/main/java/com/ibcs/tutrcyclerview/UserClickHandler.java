package com.ibcs.tutrcyclerview;

import com.ibcs.tutrcyclerview.models.User;

public interface UserClickHandler {

    void onItemClick(User user);
    void onDeleteClick(User user);
    void onEditClick(User user);
}
