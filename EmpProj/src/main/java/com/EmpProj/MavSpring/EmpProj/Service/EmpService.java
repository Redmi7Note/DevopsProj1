package com.EmpProj.MavSpring.EmpProj.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.EmpProj.MavSpring.EmpProj.Dao.EmpRepository;
import com.EmpProj.MavSpring.EmpProj.Exception.EmpExist;
import com.EmpProj.MavSpring.EmpProj.pojo.empclass;

@Component
public class EmpService {
  
	@Autowired
	public EmpRepository empobj;
	
	public Optional<empclass> repo(int id){
		Optional<empclass> empprg=empobj.findById(id);
		
		/*try {
			if(empprg.isPresent()) {}
          }
		catch(Exception Exp) {
			Exp.printStackTrace();
		}*/
        return empprg;
	}
	
	//Sorting in descending order
	public List<empclass> getAll(){
		List<empclass> list=(List<empclass>) this.empobj.findAll();
		Collections.sort(list, (d1,d2) -> Double.compare(d2.getSalary(),d1.getSalary()));
		return list;
	}
	public empclass update(empclass ent) {
		empclass old=null;
		Optional<empclass> optionald=empobj.findById((int) ent.getId());
	
				old  = optionald.get();
		        old.setSalary(ent.getSalary());
		        empobj.save(old);
		
		        return old;
	}
	public empclass saveEmp(empclass ent) throws EmpExist {
		Optional<empclass> empObj1=empobj.findById((int) ent.getId());
		if(empObj1.equals(null)) {
			empobj.save(ent);
		}else
			throw new EmpExist("Employee Already exist in Database");
		return ent;

	}
}