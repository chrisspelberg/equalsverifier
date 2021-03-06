/*
 * Copyright 2010-2012 Jan Ouwens
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.jqno.equalsverifier;

/**
 * Enum of warnings that can be suppressed in {@link EqualsVerifier}.
 * 
 * @author Jan Ouwens
 * @see EqualsVerifier#suppress(Warning...)
 */
public enum Warning {
	/**
	 * Disables annotation processing for the class under test.
	 * <p>
	 * Suppress this warning if {@link EqualsVerifier} cannot read the bytecode
	 * for the class under test. This can happen when the class does not exist
	 * in a .class file on the file system, for example when it was dynamically
	 * generated by a bytecode manipulation tool.
	 * <p>
	 * Suppressing this warning has no effect when the bytecode can be read.
	 */
	ANNOTATION,
	
	/**
	 * Disables the check, when the {@code equals} method is overridden in the
	 * class under test, that an instance of this class should be equal to an
	 * identical copy of itself.
	 * <p>
	 * Normally, it is important that an object be equal to an identical copy
	 * of itself: after all, this is the point of overriding {@code equals} in
	 * the first place. However, when the class is part of a hierarchy, but
	 * should be (pseudo-)singleton, it can be useful to suppress this warning.
	 * This can happen in certain implementations of the Null Object Pattern,
	 * for example.
	 * <p>
	 * If this warning is suppressed, and it turns out that an instance of the
	 * class under test is equal to an identical copy of itself after all,
	 * {@link EqualsVerifier} will fail.
	 */
	IDENTICAL_COPY,
	
	/**
	 * Disables checks for non-final fields on which {@code equals} and
	 * {@code hashCode} depend.
	 * <p>
	 * {@link EqualsVerifier}'s standard behaviour is to disallow non-final
	 * fields being used in {@code equals} and {@code hashCode} methods, since
	 * classes that depend on non-final fields in these methods cannot reliably
	 * be used in collections.
	 * <p>
	 * However, sometimes an external library requires that fields be
	 * non-final. An example of this are Java Beans. In such a case, suppress
	 * this warning to prevent {@link EqualsVerifier} from checking for
	 * non-final fields.
	 */
	NONFINAL_FIELDS,
	
	/**
	 * Disables checks for {@link NullPointerException} within {@code equals},
	 * {@code hashCode} and {@code toString} methods.
	 * <p>
	 * Sometimes the constructor of a class makes sure no field can be null.
	 * If this is the case, and if the fields cannot be made null later in the
	 * lifecycle of the class by setters or other methods, suppress this
	 * warning to disable the check for {@link NullPointerException}.
	 */
	NULL_FIELDS,
	
	/**
	 * Disables some of the stricter inheritance tests; use at your own risk!
	 * <p>
	 * {@link EqualsVerifier}'s standard behaviour, if T is not final and
	 * neither are its {@code equals} and {@code hashCode} methods, is to
	 * require a reference to a subclass of T for which no instance can be
	 * equal to any instance of T, to make sure that subclasses that can
	 * redefine {@code equals} or {@code hashCode} don't break the contract.
	 * <p>
	 * Some may find that too strict for their liking; suppressing this warning
	 * disables that test.
	 * 
	 * @see EqualsVerifier#withRedefinedSubclass(Class)
	 */
	STRICT_INHERITANCE,
	
	/**
	 * Disables the check that transient fields not be part of the
	 * {@code equals} contract.
	 * <p>
	 * {@link EqualsVerifier}'s standard behaviour is to disallow transient
	 * fields being used in {@code equals} and {@code hashCode} methods, since
	 * these fields may not be restored to their original state after
	 * deserialization, which would break {@code equals}.
	 * <p>
	 * If measures are taken that this will never happen, this warning can be
	 * suppressed to disable {@link EqualsVerifier}'s transience test.
	 */
	TRANSIENT_FIELDS;
}
