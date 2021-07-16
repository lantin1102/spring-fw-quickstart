package com.lantin.spring.utils.convert;

import com.lantin.spring.model.Account;
import com.lantin.spring.model.ro.AccountReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created on 2021/07/14/11:56 周三
 *
 * @author Lantin
 */
@Mapper
public interface AccountConvert {

    AccountConvert HANDLER = Mappers.getMapper(AccountConvert.class);

    Account req2Entity(AccountReq req);

}
