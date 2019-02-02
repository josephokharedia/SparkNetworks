package com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SingleValueTo {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}
