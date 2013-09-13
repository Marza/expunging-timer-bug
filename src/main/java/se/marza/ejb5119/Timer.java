package se.marza.ejb5119;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Timer.
 *
 * @author Tony Marjakangas | tony@marza.se
 */
@Startup
@Singleton
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class Timer
{
	@EJB
	private TestService service;

	/**
	 * This method will run twice and then expunge on GlassFish 3.1.1.
	 */
	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void withinTransaction()
	{
		try
		{
			this.service.throwsRuntimeExceptionWithinTransaction();
		}
		catch (final Exception e)
		{
			System.out.println("Exception was thrown and catched in withinTransaction()");
		}

		System.out.println("Finished running withinTransaction()");
	}

	/**
	 * This method will run infinitely.
	 */
	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void withoutTransaction()
	{
		try
		{
			this.service.throwsRuntimeExceptionWithoutTransaction();
		}
		catch (final Exception e)
		{
			System.out.println("Exception was thrown and catched in withoutTransaction()");
		}

		System.out.println("Finished running withoutTransaction()");
	}
}
