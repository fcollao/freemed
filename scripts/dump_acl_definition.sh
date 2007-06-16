#!/bin/bash
# $Id$
#
# Authors:
#      Jeff Buchbinder <jeff@freemedsoftware.org>
#
# FreeMED Electronic Medical Record and Practice Management System
# Copyright (C) 1999-2007 FreeMED Software Foundation
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

TMP=/tmp/$$
SCRIPTS=$(dirname "$0")

cat<<'EOF' > ${TMP}
# $Id$
#
# Authors:
#      Jeff Buchbinder <jeff@freemedsoftware.org>
#
# FreeMED Electronic Medical Record and Practice Management System
# Copyright (C) 1999-2007 FreeMED Software Foundation
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

#
#	FIXME FIXME: THIS IS NOT UPGRADE-SAFE ... IT WIPES ACL TABLES
#

EOF

TABLES=(
        acl_acl
        acl_acl_sections
        acl_acl_seq
        acl_aco
        acl_aco_map
        acl_aco_sections
        acl_aro
        acl_aro_groups
        acl_aro_groups_map
        acl_aro_map
        acl_aro_sections
        acl_aro_seq
        acl_axo
        acl_axo_groups
        acl_axo_groups_map
        acl_axo_map
        acl_axo_sections
        acl_axo_seq
        acl_groups_aro_map
        acl_groups_axo_map
        acl_phpgacl
)

mysqldump \
	--user="$(${SCRIPTS}/cfg-value DB_USER)" \
	--password="$(${SCRIPTS}/cfg-value DB_PASSWORD)" \
	"$(${SCRIPTS}/cfg-value DB_NAME)" \
	--opt --tables ${TABLES[@]} | grep -v '/*!4' >> ${TMP}

mv ${TMP} "${SCRIPTS}/../data/schema/mysql/acl.sql"
