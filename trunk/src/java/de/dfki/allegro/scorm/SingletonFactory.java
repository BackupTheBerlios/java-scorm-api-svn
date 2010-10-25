package de.dfki.allegro.scorm;
/**
 * 
 */


import java.io.Serializable;


/** This factory is used to get the singleton Scorm objects
 *  that implement the data access defined in SCORM 2004.
 *  
 *  The singleton classes which are instantiated inside can
 *  be replaced by own implementations that are derived from
 *  the original classes. The classes of this package provide
 *  only basic functionality. If you want to implement more
 *  advanced features you should use the replacement methods
 *  mentioned above.
 * 
 * @author Timo Scheuer
 *
 */
abstract class SingletonFactory implements Serializable {
	
	/** Serial version id.*/
	private static final long serialVersionUID = 1L;
	
	
	/** Class to use for instantiation of <code>Attempt</code>.*/
	private static Class<Attempt> attemptClass = Attempt.class;
	/** Class to use for instantiation of <code>AttemptLearnerPreferences</code>.*/
	private static Class<AttemptLearnerPreferences> attemptLearnerPreferencesClass = AttemptLearnerPreferences.class;
	/** Class to use for instantiation of <code>Interactions</code>.*/
	private static Class<Interactions> interactionsClass = Interactions.class;
	/** Class to use for instantiation of <code>Learner</code>.*/
	private static Class<Learner> learnerClass = Learner.class;
	/** Class to use for instantiation of <code>LearnerComments</code>.*/
	private static Class<LearnerComments> learnerCommentsClass = LearnerComments.class;
	/** Class to use for instantiation of <code>LMSComments</code>.*/
	private static Class<LMSComments> lMSCommentsClass = LMSComments.class;
	/** Class to use for instantiation of <code>LoginSession</code>.*/
	private static Class<LoginSession> loginSessionClass = LoginSession.class;
	/** Class to use for instantiation of <code>Objectives</code>.*/
	private static Class<Objectives> objectivesClass = Objectives.class;
	/** Class to use for instantiation of <code>SharedDataStore</code>.*/
	private static Class<SharedDataStore> sharedDataStoreClass = SharedDataStore.class;
	/** Class to use for instantiation of <code>State</code>.*/
	private static Class<State> stateClass = State.class;
	/** Class to use for instantiation of <code>Time</code>.*/
	private static Class<Time> timeClass = Time.class;
	
	/** The singleton <code>Attempt</code> instance.*/
	private static Attempt singletonAttempt;
	/** The singleton <code>AttemptLearnerPreferences</code> instance.*/
	private static AttemptLearnerPreferences singletonAttemptLearnerPreferences;
	/** The singleton <code>Interactions</code> instance.*/
	private static Interactions singletonInteractions;
	/** The singleton <code>Learner</code> instance.*/
	private static Learner singletonLearner;
	/** The singleton <code>LearnerComments</code> instance.*/
	private static LearnerComments singletonLearnerComments;
	/** The singleton <code>LMSComments</code> instance.*/
	private static LMSComments singletonLMSComments;
	/** The singleton <code>LoginSession</code> instance.*/
	private static LoginSession singletonLoginSession;
	/** The singleton <code>Objectives</code> instance.*/
	private static Objectives singletonObjectives;
	/** The singleton <code>SharedDataStore</code> instance.*/
	private static SharedDataStore singletonSharedDataStore;
	/** The singleton <code>State</code> instance.*/
	private static State singletonState;
	/** The singleton <code>Time</code> instance.*/
	private static Time singletonTime;
	

	/** Ctor.
	 * 
	 */
	public SingletonFactory() {
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setAttemptClass(Class<Attempt> c) {
		attemptClass = c;
		singletonAttempt = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized Attempt getAttemptInstance() {
		if (singletonAttempt == null)
			try {
				singletonAttempt = attemptClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						attemptClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						attemptClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonAttempt;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setAttemptLearnerPreferencesClass(Class<AttemptLearnerPreferences> c) {
		attemptLearnerPreferencesClass = c;
		singletonAttemptLearnerPreferences = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized AttemptLearnerPreferences getAttemptLearnerPreferencesInstance() {
		if (singletonAttemptLearnerPreferences == null)
			try {
				singletonAttemptLearnerPreferences = attemptLearnerPreferencesClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						attemptLearnerPreferencesClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						attemptLearnerPreferencesClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonAttemptLearnerPreferences;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setInteractionsClass(Class<Interactions> c) {
		interactionsClass = c;
		singletonInteractions = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized Interactions getInteractionsInstance() {
		if (singletonInteractions == null)
			try {
				singletonInteractions = interactionsClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						interactionsClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						interactionsClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonInteractions;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setLoginSessionClass(Class<LoginSession> c) {
		loginSessionClass = c;
		singletonLoginSession = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized LoginSession getLoginSessionInstance() {
		if (singletonLoginSession == null)
			try {
				singletonLoginSession = loginSessionClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						loginSessionClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						loginSessionClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonLoginSession;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setObjectivesClass(Class<Objectives> c) {
		objectivesClass = c;
		singletonObjectives = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	/** The objectives are available during the actual
	 *  attempt. They can change from <code>CommunicationSession</code>
	 *  to <code>CommunicationSession</code>. This can happen
	 *  when updates of the sequencing (specified in the SCORM
	 *  package) are propagated to the objectives.
	 *   
	 * @return singleton instance
	 */
	static synchronized Objectives getObjectivesInstance() {
		if (singletonObjectives == null)
			try {
				singletonObjectives = objectivesClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						objectivesClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						objectivesClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonObjectives;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setSharedDataStoreClass(Class<SharedDataStore> c) {
		sharedDataStoreClass = c;
		singletonSharedDataStore = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized SharedDataStore getSharedDataStoreInstance() {
		if (singletonSharedDataStore == null)
			try {
				singletonSharedDataStore = sharedDataStoreClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						sharedDataStoreClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						sharedDataStoreClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonSharedDataStore;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setStateClass(Class<State> c) {
		stateClass = c;
		singletonState = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized State getStateInstance() {
		if (singletonState == null)
			try {
				singletonState = stateClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						stateClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						stateClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonState;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setTimeClass(Class<Time> c) {
		timeClass = c;
		singletonTime = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized Time getTimeInstance() {
		if (singletonTime == null)
			try {
				singletonTime = timeClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						timeClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						timeClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonTime;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setLearnerClass(Class<Learner> c) {
		learnerClass = c;
		singletonLearner = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized Learner getLearnerInstance() {
		if (singletonLearner == null)
			try {
				singletonLearner = learnerClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						learnerClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						learnerClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonLearner;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setLearnerCommentsClass(Class<LearnerComments> c) {
		learnerCommentsClass = c;
		singletonLearnerComments = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized LearnerComments getLearnerCommentsInstance() {
		if (singletonLearnerComments == null)
			try {
				singletonLearnerComments = learnerCommentsClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						learnerCommentsClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						learnerCommentsClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonLearnerComments;
	}

	/** Replace the class to be used for instantiation.
	 * 
	 * @param c  new class for instantiation
	 */
	public static void setLMSCommentsClass(Class<LMSComments> c) {
		lMSCommentsClass = c;
		singletonLMSComments = null;
	}

	/** Get the singleton instance. If you replaced the class
	 *  to use for instantiation and an error occurs during
	 *  instantiation then a <code>RuntimeException</code>
	 *  encapsulating the original exception is thrown.
	 * 
	 * @return singleton instance
	 */
	static synchronized LMSComments getLMSCommentsInstance() {
		if (singletonLMSComments == null)
			try {
				singletonLMSComments = lMSCommentsClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation of class '" +
						lMSCommentsClass.getName() + "' failed!", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Instantiation of class '" +
						lMSCommentsClass.getName() +
						"' failed (permission problem)!", e);
			}
		return singletonLMSComments;
	}

}
