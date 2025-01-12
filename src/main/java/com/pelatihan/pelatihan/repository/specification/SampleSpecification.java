package com.pelatihan.pelatihan.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.pelatihan.pelatihan.model.Sample;

public class SampleSpecification {
    public static Specification<Sample> containName(String name){
        return (root,query,builder) -> 
                builder.like(root.get("name"), "%" + name + "%");
    }
    
    public static Specification<Sample> equalCode(String code){
        return (root,query,builder) -> 
                builder.equal(root.get("code"), code);
    }

    public static Specification<Sample> equalStatus(Boolean status){
        return (root,query,builder) -> 
                builder.equal(root.get("status"), status);
    }
}
