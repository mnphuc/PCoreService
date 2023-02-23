package com.phuc.pcoreservice.repository;

import com.phuc.pcoreservice.dto.GmailDTO;
import com.phuc.pcoreservice.dto.InfoGmailDTO;
import com.phuc.pcoreservice.model.GmailModel;

import java.util.List;

public interface IGmailRepo {

    InfoGmailDTO getGmail();

    List<String> getListGmailByVps(String ipAddress);

    boolean updateStatusLoginGmail(Integer gmailId);

    Boolean importGmail(List<GmailModel> list);

}
