package de.wwu.muggl.vm.initialization;

import java.util.Enumeration;
import java.util.Hashtable;

import de.wwu.muggl.vm.classfile.structures.Field;
import de.wwu.muggl.vm.threading.Monitor;

/**
 * This class represents a concrete instance of a ClassFile. It especially offers access
 * to the instance fields, as well as means of wrapping java objects to an objectref and
 * of extracting the fields of object references to insert them into java objects.
 *
 * @author Tim Majchrzak
 * @version 1.0.0, 2010-03-10
 */
public class Objectref extends FieldContainer implements ReferenceValue {
	// Fields
	private InitializedClass staticReference;
	private boolean primitiveWrapper;
	private Monitor monitor;
	private long instantiationNumber;

	/**
	 * Private constructor to get concrete instances of an initialized class. These instances
	 * have a reference to the InitializedClass, which keeps control of the static
	 * fields. The concrete instance itself keeps control of instance fields.
	 * @param staticReference The InitializedClass (which has been generated by the ClassFile).
	 * @param primitiveWrapper Toggles the usage of the Objectref as a wrapper for primitive types.
	 */
	public Objectref(InitializedClass staticReference, boolean primitiveWrapper) {
		this.staticReference = staticReference;
		this.primitiveWrapper = primitiveWrapper;
		this.instantiationNumber = staticReference.getClassFile().getClassLoader()
				.getNextInstantiationNumber();
	}

	/**
	 * Getter for the monitor associated with this objectref.
	 * @return The monitor associated with this objectref (might be null if there is no such one).
	 */
	public Monitor getMonitor() {
		return this.monitor;
	}

	/**
	 * Sets the monitor to be associated with this objectref. This will only work
	 * if there currently is no monitor associated.
	 * @param monitor The monitor that will be associated with this objectref.
	 */
	public void setMonitor(Monitor monitor) {
		if (this.monitor == null) this.monitor = monitor;
	}

	/**
	 * Return a String representation of the represented Class.
	 * @return A String representation of the represented Class.
	 */
	@Override
	public String toString() {
		return "Reference of " + this.staticReference.getClassFile().getName() + " (id: " + this.instantiationNumber + ")";
	}

	/**
	 * Return false, since this is not a reference to an array.
	 * @return false, since this is not a reference to an array.
	 */
	public boolean isArray() {
		return false;
	}

	/**
	 * Get the name of this reference value.
	 *
	 * @return The name of this reference value.
	 */
	public String getName() {
		return this.staticReference.getClassFile().getName();
	}

	/**
	 * Getter for the corresponding InitializedClass.
	 * @return The corresponding InitializedClass.
	 */
	public InitializedClass getInitializedClass() {
		return this.staticReference;
	}

	/**
	 * Returns true, if the ReferenceValue is wrapping a primitive type.
	 * @return true, if the ReferenceValue is wrapping a primitive type, false otherwise.
	 */
	public boolean isPrimitive() {
		return this.primitiveWrapper;
	}

	/**
	 * Getter for the instantiation number. Instantiation numbers can be used
	 * to determine which of two ReferenceValues has been generated earlier.
	 * @return The instantiation number.
	 */
	public long getInstantiationNumber() {
		return this.instantiationNumber;
	}

	/**
	 * Check if the supplied object is equal to this object reference. 
	 * 
	 * @param obj The object to check equality with.
	 * @return true, if the supplied object is equal to this object reference, false otherwise.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Objectref) {
			return equals((Objectref) obj);
		}
		return false;
	}
	
	/**
	 * Check if the supplied object reference is equal to this object reference. Equality is given if
	 * <ul>
	 * <li>the represented class of the static reference is equal,</li>
	 * <li>there is the same number of static fields, each field exists in both references and it has the same value and</li>
	 * <li>there is the same number of instance fields, each field exists in both references and it has the same value.</li>
	 * </ul>
	 *
	 * @param objectref The object reference to check equality with.
	 * @return true, if the supplied object reference is equal to this object reference, false otherwise.
	 */
	public boolean equals(Objectref objectref) {
		// First compare the ClassFile references.
		if (this.staticReference.getClassFile() != objectref.staticReference.getClassFile()) return false;

		// Then compare all static fields. First by size, the element by element.
		if (this.staticReference.fields.size() != objectref.staticReference.fields.size()) return false;
		Enumeration<Field> fieldEnumerator = this.staticReference.fields.keys();
		while (fieldEnumerator.hasMoreElements()) {
			Field field = fieldEnumerator.nextElement();
			if (!objectref.staticReference.fields.containsKey(field)) return false;
			if (!(ObjectComparator.compareObjects(this.staticReference.fields.get(field), objectref.staticReference.fields.get(field)))) return false;
		}

		// Finally compare all instance fields.
		if (this.fields.size() != objectref.fields.size()) return false;
		fieldEnumerator = this.fields.keys();
		while (fieldEnumerator.hasMoreElements()) {
			Field field = fieldEnumerator.nextElement();
			if (!objectref.fields.containsKey(field)) return false;
			if (!(ObjectComparator.compareObjects(this.fields.get(field), objectref.fields.get(field)))) return false;
		}

		// Reaching this point means that the values are equal.
		return true;
	}

	/**
	 * Return the unique instantiation number of this object reference.
	 *
	 * @return The instantiation number of this object reference.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) this.instantiationNumber;
	}

	/**
	 * Reset the cached java object and assign a value to a Field.
	 * @param field The Field to get a new value assigned.
	 * @param value The new value.
	 * @throws FieldAccessError If the Field does not belong to the Class represented by this InitializedClass.
	 */
	@Override
	public void putField(Field field, Object value) {
		super.putField(field, value);
	}

	/**
	 * Getter for the fields.
	 *
	 * @return The fields Hashtable.
	 */
	public Hashtable<Field, Object> getFields() {
		return this.fields;
	}

}
