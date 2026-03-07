package com.example.CourseManagementSystem.Service;

import com.example.CourseManagementSystem.Entity.CourseMaterial;
import com.example.CourseManagementSystem.Repository.CourseMaterialRepository;
import com.example.CourseManagementSystem.dto.MaterialResponseDto;
import com.example.CourseManagementSystem.dto.MaterialUploadDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMaterialService {

    private final CourseMaterialRepository materialRepository;
    private final ModelMapper modelMapper;

    private final String uploadDir = "uploads/";

    public MaterialResponseDto uploadMaterial(MaterialUploadDto request){

        MultipartFile file = request.getFile();

        String fileName = file.getOriginalFilename();

        try {

            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e){
            throw new RuntimeException("File upload failed");
        }

        CourseMaterial material = new CourseMaterial();

        material.setTitle(request.getTitle());
        material.setFileName(fileName);
        material.setFileType(file.getContentType());
        material.setFileUrl(uploadDir + fileName);

        materialRepository.save(material);

        return modelMapper.map(material, MaterialResponseDto.class);
    }

    public List<MaterialResponseDto> getMaterialsByCourse(Long courseId){

        return materialRepository.findByCourseId(courseId)
                .stream()
                .map(m -> modelMapper.map(m, MaterialResponseDto.class))
                .collect(Collectors.toList());
    }
    public ResponseEntity<Resource> downloadMaterial(Long id){

        CourseMaterial material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path path = Paths.get(material.getFileUrl());

        try {

            Resource resource = new UrlResource(path.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + material.getFileName() + "\"")
                    .body(resource);

        } catch (Exception e) {

            throw new RuntimeException("File download failed");

        }
    }
}