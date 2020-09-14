package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Branch;
import co.radiantmic.lpapp.exception.BadRequestException;
import co.radiantmic.lpapp.exception.EntityNotFoundException;
import co.radiantmic.lpapp.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;


    public Branch createBranch(Branch branch){
        try {
            Branch createdBranch = branchRepository.save(branch);
            return createdBranch;
        }
        catch(Exception ex){
            throw new BadRequestException("Cannot create a branch | "+ex.toString());
        }
    }

    public Branch updateBranch(Branch branch){
        try {
            Optional<Branch> optionalBranch= branchRepository.findById(branch.getBranchId());
            if(!optionalBranch.isPresent()){
                throw new EntityNotFoundException("Cannot find the entity to update");
            }
            else{
                Branch branchToUpdate=optionalBranch.get();
                branchToUpdate.setBank(branch.getBank());
                branchToUpdate.setBranchName(branch.getBranchName());
                branchToUpdate.setDistrict(branch.getDistrict());
                branchToUpdate.setProvince(branch.getProvince());
                Branch updatedBranch = branchRepository.save(branch);
                return updatedBranch;
            }
        }
        catch(Exception ex){
            throw new BadRequestException("Cannot create a branch | "+ex.toString());
        }
    }

    public List<Branch> getAllBranches(){
        try {
            List<Branch> listBranches = branchRepository.findAll();
            System.out.println(listBranches);
            return listBranches;
        }
        catch(Exception ex){
            throw new BadRequestException("Cannot create a branch | "+ex.toString());
        }
    }


}
