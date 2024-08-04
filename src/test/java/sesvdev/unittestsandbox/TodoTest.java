package sesvdev.unittestsandbox;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

	@Test
	void shouldCompareJson() throws JSONException {

		var expectedJson =
		"""
		{
			"todos" : [ 
			{
				"name": "study",
				"completed": false
			},
			{
				"name": "laundry",
				"completed": true
			}
		]
		}		
		""";

		var todoResponse = getRestData();

		JSONAssert.assertNotEquals(expectedJson, todoResponse, true);
	}

	@Test
	void shouldHaveLengthOfSizeTwo() {
		var todoResponse = getRestData();
        Integer todoResponseLength = JsonPath.read(todoResponse, "$.todos.length()");
        assertEquals(2, todoResponseLength);
	}


	@Test
	void shouldContainEqualData() {
		var expectedJson =
                """
                        {
                        	"todos" : [ 
                        	{
                        		"name": "laundry",
                        		"completed": true
                        	},
                        	{
                        		"name": "study",
                        		"completed": false
                        	}
                        ]
                        }		
                        """;

		String firstTodoName = JsonPath.read(expectedJson, "$.todos[0].name");
		boolean firstTodoCompleted = JsonPath.read(expectedJson, "$.todos[0].completed");

		String secondTodoName = JsonPath.read(expectedJson, "$.todos[1].name");
		boolean secondTodoCompleted = JsonPath.read(expectedJson, "$.todos[1].completed");

		Assertions.assertAll(
                () -> assertEquals("laundry", firstTodoName),
                () -> Assertions.assertTrue(firstTodoCompleted),
                () -> assertEquals("study", secondTodoName),
                () -> Assertions.assertFalse(secondTodoCompleted)
        );
	}

	private static String getRestData() {
		return
		"""
		{
			"todos" : [ 
			{
				"name": "laundry",
				"completed": true
			},
			{
				"name": "study",
				"completed": false
			}
		]
		}		
		""";
	}


}