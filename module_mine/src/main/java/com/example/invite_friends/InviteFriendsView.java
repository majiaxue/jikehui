package com.example.invite_friends;

import com.example.bean.InviteBean;
import com.example.invite_friends.adapter.InviteVpAdapter;
import com.example.mvp.IView;

import java.util.List;

public interface InviteFriendsView extends IView {
    void loadVP(InviteVpAdapter adapter, int size);
}
