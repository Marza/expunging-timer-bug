package se.marza.ejb5119;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Test service.
 *
 * @author Tony Marjakangas | tony@marza.se
 */
@Stateless
public class TestService
{
	/**
	 * Throws RuntimeException within a transaction.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void throwsRuntimeExceptionWithinTransaction()
	{
		throw new RuntimeException();
	}

	/**
	 * Throws RuntimeException without a transaction.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void throwsRuntimeExceptionWithoutTransaction()
	{
		throw new RuntimeException();
	}
}
