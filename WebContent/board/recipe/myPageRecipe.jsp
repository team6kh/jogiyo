<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<div align="left">
	<h2>����������</h2>
	<ul>
		<li><a href="">���Ÿ��</a></li>
		<li><a href="">����</a></li>
		<li><a href="">��ǰ Q&A</a></li>
		<li><a href="">1:1 ����</a></li>
		<li><a href="">ȸ����������</a></li>
		
	</ul>

</div>


<div align="center">
	<h2>����������</h2>
	<div>
		<p>
			<strong>${resultClass.Id}</strong>�� �ݰ����ϴ�!
		</p>

	</div>



	<script type="text/javascript">
		
	</script>





	<h3>���Ÿ��</h3>

	<div>
		<fieldset>
			<legend>��ȸ�Ⱓ �˻�</legend>
			<strong>��ȸ�Ⱓ</strong>
			<div>
				<div>
					<label id="search_period_7D" for="radio01" onclick="">1����</label>
					<label id="search_period_15D" for="radio02" onclick="">15��</label>
					<label id="search_period_1M" for="radio03" onclick="">1����</label> 
					<label id="search_period_3M" for="radio04" onclick="">3����</label> 
					<label id="search_period_6M" for="radio05" onclick="">6����</label>
				</div>
				<div>
					<select id="search_start_year" name="search_start_year">
					    <option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option></select> ��
					 <select id="search_start_month" name="search_start_month">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option></select> ��
					<select id="search_start_date" name="search_start_date">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option></select> ��  ~  
					<select id="search_end_year" name="search_end_year">
					    <option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option></select> �� 
					<select id="search_end_month" name="search_end_month">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option></select> ��
					<select	id="search_end_date" name="search_end_date">
					    <option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option></select> ��
					<button type="submit" onclick="">
						<span>��ȸ�ϱ�</span>
					</button>
				</div>
			</div>
		</fieldset>
	</div>
	<div >
		<ul>
			<li><a href="" >��ü ���Ÿ��</a></li>
			<li><a href="" >����</a></li>
			
		</ul>
	</div>
	<table cellspacing="0" width="100%" summary="���Ÿ���� �ֹ�����(�ֹ���ȣ),�ֹ� ��ǰ ����, �� �����ݾ�,���µ��� �����ϴ� ���̺�">
		<caption>���Ÿ��</caption>
		<colgroup>
			<col width="115">
			<col width="413">
			<col width="125">
			<col width="99">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">�ֹ�����<br>(�ֹ���ȣ)
				</th>
				<th scope="col">�ֹ� ��ǰ ����</th>
				<th scope="col">�� ���� �ݾ�</th>
				<th scope="col">����</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="4">������ ��ǰ�� �����ϴ�.</td>
			</tr>
		</tbody>
	</table>
	<div></div>

	</div>
</div>

<div id="social_div" style="display: none" height="1000"></div>

<div id="buylist_div" style="display: none" height="1000">
	<div id="buylist_title">
		<h1>
			���Ÿ��
		</h1>
		<p>
			���� &gt; ���������� &gt; <span>���Ÿ��</span>
		</p>
	</div>
	<div id="buylist_content_area"></div>
</div>

<div id="buydetail_div" style="display: none" height="1000">
	<div id="buydetail_title">
		<h1>
			<img
				src="http://image.wemakeprice.com/images/mypage/title_ticket.gif"
				alt="���Ÿ��">
		</h1>
		<p class="location">
			���� &gt; ���������� &gt; ���Ÿ�� &gt; <span class="location_on">Ƽ��/��ǰ����</span>
		</p>
	</div>
	<div id="buylist_content_area"></div>
</div>

