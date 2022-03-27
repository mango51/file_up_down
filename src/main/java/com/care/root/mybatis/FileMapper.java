package com.care.root.mybatis;

import java.util.List;

import org.springframework.ui.Model;

import com.care.root.dto.ShoesDTO;

public interface FileMapper {
	public void saveData(ShoesDTO dto);
	public List<ShoesDTO> getShoesImage();
}
