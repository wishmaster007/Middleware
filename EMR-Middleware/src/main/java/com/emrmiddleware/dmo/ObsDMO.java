package com.emrmiddleware.dmo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emrmiddleware.dto.ObsDTO;

public interface ObsDMO {

	@Select("select obs.uuid as openmrs_obsuuid ,encounter.uuid as openmrs_encounteruuid, CASE  WHEN obs.value_numeric IS NOT NULL THEN CAST(obs.value_numeric AS CHAR(50) CHARACTER SET utf8) WHEN obs.value_text IS NOT NULL THEN obs.value_text END as value,obs.concept_id as conceptid,obs.creator from obs,encounter,visit,location where obs.encounter_id=encounter.encounter_id     and encounter.visit_id=visit.visit_id and visit.location_id=location.location_id and  obs.obs_datetime>=#{lastchangedtime} and obs.voided=0 and location.uuid=#{locationuuid}")
	public ArrayList<ObsDTO> getObs(@Param("lastchangedtime") Date lastchangedtime,@Param("locationuuid") String locationuuid);
}
