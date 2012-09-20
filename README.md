EJB5119: Expunging timer bug with GlassFish
===================

I experienced a wierd behaviour on GlassFish 3.1.1 where I have a Timer class annotated with @Schedule for cron functionality. This class wraps everything it does within a try-catch block to ensure the timer doesn't crash. But when calling a method on an EJB from the Timer class where the method call is running in a transaction and the method throws any type of RuntimeException the method on the timer will end up in a retry loop and the default configuration for GlassFish 3.1.1 is to retry up to 1 time after 5 seconds and if it fails again the timer will expunge.

You would expect that because everything is wrapped within try-catch that the method on the timer won't fail but it does. Calling a method without transaction scope that throws a RuntimeException won't expunge the timer as expected.

I tested this on JBoss 7.1.1 Final aswell and the bug didn't occurr there, but it did occurr on the latest version of GlassFish which is 3.1.2.2

Solution
-------------------

The solution is to catch any unchecked exceptions within the transaction scoped method and re-throwing them as checked exceptions instead.