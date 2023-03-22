package com.company.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class ListChecker {
	
	public static <T> boolean isSorted(List<T> list, Comparator<? super T> comparator) {		
		boolean sorted = true;
		
		if(list.size() > 1) {
			Iterator<T> iter = list.iterator();
			T lhs = iter.next();
			
			while(sorted && iter.hasNext()) {
				T rhs = iter.next();
				sorted = comparator.compare(lhs, rhs) <= 0;
				lhs = rhs;
			}
		}
		
		return sorted;
	}
}
