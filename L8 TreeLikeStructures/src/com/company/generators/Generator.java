package com.company.generators;

import java.util.List;

public interface Generator<T> {
	List<T> generate(int size);
}
