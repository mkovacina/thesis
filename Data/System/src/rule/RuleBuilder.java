package rule;

import org.dom4j.Element;


public interface RuleBuilder
{
	Rule create( Element root );
}