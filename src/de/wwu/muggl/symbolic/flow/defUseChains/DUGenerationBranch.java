package de.wwu.muggl.symbolic.flow.defUseChains;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import de.wwu.muggl.symbolic.flow.defUseChains.structures.Def;
import de.wwu.muggl.symbolic.flow.defUseChains.structures.DefUseVariable;
import de.wwu.muggl.symbolic.flow.defUseChains.structures.ObjectAttributeCandidate;
import de.wwu.muggl.vm.classfile.Limitations;
import de.wwu.muggl.vm.classfile.structures.Method;

/**
 * A DUGenerationBranch is a class used when finding def-use chains in methods. Its visibility is
 * not public and it can only be used within this package.<br />
 * <br />
 * When finding def-use chains, branching is needed at those instruction that may lead to a jump in
 * the control flow (such as conditional jumps, switching instructions etc.). To check both parts,
 * this class is used. It is initialized with the definitions yet found, the variables
 * loaded since the last usage and the current pc. With these information all possibly branches can
 * be visited since former states can be restored.
 *
 * @author Tim Majchrzak
 * @version 1.0.0, 2010-12-14
 */
class DUGenerationBranch {
	// State of the branch.
	private DUGenerationBranch invokedBy;
	private int invokedFromPc;
	private Method method;
	private int pc;
	private Map<DefUseVariable, Def> defs;
	private Set<DefUseVariable> variables;
	private int currentStackPosition;
	private Map<Integer, ObjectAttributeCandidate> objectrefsOnStack;

	/**
	 * Construct the empty branch.
	 *
	 * @param method The method this branch was generated by.
	 * @param pc The current pc.
	 */
	public DUGenerationBranch(Method method, int pc) {
		this.method = method;
		this.pc = pc;
		this.defs = new TreeMap<DefUseVariable, Def>();
		this.variables = new TreeSet<DefUseVariable>();
		this.currentStackPosition = 0;
		this.objectrefsOnStack = new HashMap<Integer, ObjectAttributeCandidate>();
	}
	
	/**
	 * Construct the empty branch specifying the DUGenerationBranch that lead to the creation of this branch.
	 *
	 * @param invokedBy The DUGenerationBranch that lead to the creation of this branch.
	 * @param method The method this branch was generated by.
	 * @param pc The current pc.
	 */
	public DUGenerationBranch(DUGenerationBranch invokedBy, Method method, int pc) {
		this(method, pc);
		this.invokedBy = invokedBy;
		if (invokedBy != null) {
			this.invokedFromPc = invokedBy.getPC();
		}
	}

	/**
	 * Construct the branch.
	 * 
	 * @param method The method this branch was generated by.
	 * @param pc The current pc.
	 * @param defs The definitions yet found, mapped by the corresponding variables.
	 * @param variables The variables loaded since the last usage.
	 * @param currentStackPosition The position of the currently topmost element of the stack.
	 */
	private DUGenerationBranch(Method method, int pc, Map<DefUseVariable, Def> defs,
			Set<DefUseVariable> variables, int currentStackPosition) {
		this.method = method;
		this.pc = pc;
		this.defs = defs;
		this.variables = variables;
		this.currentStackPosition = currentStackPosition;
		this.objectrefsOnStack = new HashMap<Integer, ObjectAttributeCandidate>();
	}

	/**
	 * Construct the branch specifying the DUGenerationBranch that lead to the creation of this
	 * branch.
	 * 
	 * @param invokedBy The DUGenerationBranch that lead to the creation of this branch.
	 * @param method The method this branch was generated by.
	 * @param pc The current pc.
	 * @param defs The definitions yet found, mapped by the corresponding variables.
	 * @param variables The variables loaded since the last usage.
	 * @param currentStackPosition The position of the currently topmost element of the stack.
	 */
	private DUGenerationBranch(DUGenerationBranch invokedBy, Method method, int pc,
			Map<DefUseVariable, Def> defs, Set<DefUseVariable> variables, int currentStackPosition) {
		this(method, pc, defs, variables, currentStackPosition);
		this.invokedBy = invokedBy;
		if (invokedBy != null) {
			this.invokedFromPc = invokedBy.getPC();
		}
	}

	/**
	 * Get the DUGenerationBranch that lead to the creation of this branch.
	 *
	 * @return The DUGenerationBranch that lead to the creation of this branch; or null, if no such
	 *         branch was specified.
	 */
	public DUGenerationBranch getInvokedBy() {
		return this.invokedBy;
	}

	/**
	 * Get the pc the DUGenerationBranch hat at the moment that lead to the creation of this branch.
	 * 
	 * @return The pc the DUGenerationBranch hat at the moment that lead to the creation of this
	 *         branch; or null, if no such branch was specified.
	 */
	public int getInvokedFromPc() {
		return this.invokedFromPc;
	}

	/**
	 * Get the method.
	 *
	 * @return The method of this DUGenerationBranch.
	 */
	public Method getMethod() {
		return this.method;
	}

	/**
	 * Get the pc.
	 *
	 * @return The pc the state described by this DUGenerationBranch was recorded at.
	 */
	public int getPC() {
		return this.pc;
	}

	/**
	 * Set the pc.
	 *
	 * @param pc An updated pc for this DUGenerationBranch.
	 */
	public void setPC(int pc) {
		// pc too great?
		if (pc >= Limitations.MAX_CODE_LENGTH) {
			pc -= Limitations.MAX_CODE_LENGTH;
		}

		// Set it.
		this.pc = pc;
	}

	/**
	 * Add the specified definition to the set of definitions.
	 *
	 * @param def The definition just encountered.
	 */
	public void addDef(Def def) {
		this.defs.put(def.getVariable(), def);
	}

	/**
	 * Fetch the definition that corresponds to the supplied variable. If no such definition was
	 * found, return null to indicate this.
	 * 
	 * @param variable The variable to fetch the definition for.
	 * @return The corresponding definition; or null if no such definition exists.
	 */
	public Def fetchDef(DefUseVariable variable) {
		 return this.defs.get(variable);
		 }
	
	/**
	 * Get the loaded variables.
	 * 
	 * @return The set of loaded variables.
	 */
	public Set<DefUseVariable> getVariable() {
		return this.variables;
	}

	/**
	 * Add the specified variable to the set of loaded variables.
	 *
	 * @param variable The variable just loaded.
	 */
	public void addVariable(DefUseVariable variable) {
		this.variables.add(variable);
	}

	/**
	 * Unload the specified variable from the set of loaded variables.
	 *
	 * @param variable The variable to unload.
	 */
	public void unloadVariable(DefUseVariable variable) {
		this.variables.remove(variable);
	}

	/**
	 * Get the value of the counter for the current stack position and increase its value by one.
	 *
	 * @return The value of the counter for the current stack position.
	 */
	public int getStackPosition() {
		int temp = this.currentStackPosition;
		this.currentStackPosition++;
		return temp;
	}
	
	/**
	 * Get the value of the counter for the current stack position without increasing it.
	 *
	 * @return The value of the counter for the current stack position.
	 */
	public int copyStackPosition() {
		return this.currentStackPosition;
	}

	/**
	 * Decrease the counter for the current stack position by one.
	 *
	 * @return The (already decreased) value of the counter for the current stack position.
	 * @throws IllegalStateException If the current stack position is zero.
	 */
	public int decreaseStackPosition() {
		if (this.currentStackPosition > 0) {
			// Remove an object reference that might be on the stack.
			ObjectAttributeCandidate candidate = this.objectrefsOnStack
					.get(this.currentStackPosition);
			if (candidate != null) {
				this.variables.remove(candidate);
			}
			
			// Decrease and return.
			this.currentStackPosition--;
			return this.currentStackPosition;
		}
		throw new IllegalStateException("This would cause an EmptyStackException."); // TODO
	}

	/**
	 * Get the object reference known to be at the specified stack position.
	 * 
	 * @param stackPosition A position on the operand stack.
	 * @return The object reference stored at specified stack position; or null if there is nothing
	 *         stored at the position or the position is invalid.
	 */
	public ObjectAttributeCandidate getObjectrefAt(int stackPosition) {
		return this.objectrefsOnStack.get(stackPosition);
	}
	
	/**
	 * Get the object reference known to be at the current stack position.
	 * 
	 * @return The object reference stored at current stack position; or null if there is nothing
	 *         stored at the position or the position is invalid.
	 */
	public ObjectAttributeCandidate getObjectrefAtCurrentPos() {
		return this.objectrefsOnStack.get(this.currentStackPosition);
	}

	/**
	 * Get a safe copy of this generation branch. This makes sure that any mutable data-structured
	 * are copied and changes to the copied branch will not affect this one.
	 *
	 * @return The copied branch.
	 */
	public DUGenerationBranch getSafeCopy() {
		// Copy map and set including all sub structures.
		Map<DefUseVariable, Def> defs = new TreeMap<DefUseVariable, Def>();
		for (Entry<DefUseVariable, Def> entry : this.defs.entrySet()) {
			defs.put(entry.getKey(), entry.getValue());
		}
		
		Set<DefUseVariable> variables = new TreeSet<DefUseVariable>();
		for (DefUseVariable variable : this.variables) {
			variables.add(variable);
		}
		
		// Construct new object and return it.
		return new DUGenerationBranch(this.invokedBy, this.method, this.pc, defs, variables, this.currentStackPosition);
	}

	/**
	 * Get a String representation of the def-use generation branch.
	 *
	 * @return A String representation of the def-use generation branch.
	 */
	@Override
	public String toString() {
		String output = "Def-use generation branch\n"
				+ "Method: " + this.method + "\n"
				+ "Line number: " + this.pc + "\n"
				+ "Stack position: " + this.currentStackPosition + "\n"
				+ "Variables loaded:\n";
		Iterator<DefUseVariable> variableIterator = this.variables.iterator();
		while (variableIterator.hasNext()) {
			output += variableIterator.next().toString() + "\n";
		}
		output += "Definitions:\n";
		Iterator<Entry<DefUseVariable, Def>> defIterator = this.defs.entrySet().iterator();
		while (defIterator.hasNext()) {
			output += defIterator.next().getValue().toString() + "\n";
		}

		return output;
	}

}
